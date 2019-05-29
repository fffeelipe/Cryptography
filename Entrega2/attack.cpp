#include "attack.h"
#include <bits/stdc++.h>


Attack::Attack(){}

std::vector<int> Attack::getFrecuencia(std::string& s){
    std::vector<int> ans(TAM,0);
    for (auto&c: s){
        ans[c-'A']++;
    }
    return ans;
}
double Attack::indiceCoincidenciaFrecuencias(std::vector<int> frecuencias){
    double ans=0.0;
    int t=0;
    for (auto i : frecuencias){
        t+= i;
        ans+= i*(i-1);
    }
    return ans/(t*(t-1)) ;
}
double Attack::indiceCoincidencia ( std::string& s ){
    return indiceCoincidenciaFrecuencias( getFrecuencia(s) );
}
std::vector<double> Attack::indiceCoincidenciaTamanoClave ( std::string& s , const int& n){
    std::vector<double> ans (n,0);
    std::vector< std::vector<int> > freq (n, std::vector<int> (TAM));
    for (int i=0;i<s.size(); ++i){
        freq[i%n][s[i]-'A']++;
    }
    for (int i =0;i<n;++i){
        ans[i]= indiceCoincidenciaFrecuencias(freq[i]);
    }
    return ans;
}
std::vector<std::vector<double> > Attack::indiceEsperadoTamanoClave (std::string& s,  int& n){
    std::vector<std::vector<double> > ans (n);
    std::vector< std::vector<int> > freq (n, std::vector<int> (TAM));
    for (int i=0;i<s.size(); ++i){
        freq[i%n][s[i]-'A']++;
    }
    std::vector<double>prob = {8.167,1.492,2.782,4.253,12.702,2.228,2.015,6.094,6.966,0.153,0.772,4.025,2.406,6.749,7.507,1.929,0.095,5.987,6.327,9.056,2.758,0.978,2.360,0.150,1.974,0.074,};
    for (int i=0;i<n;++i){
        for (int j=0;j<TAM;j++){
            double a=0;
            for (int k=0;i+ k<TAM;++k){
                int newInd = (((k-j)%TAM)+TAM)%TAM;
                a+= prob[newInd]*freq[i][k];
            }
            ans[i].push_back(a/(double)(s.size()/n));
        }
    }
    return ans;
}

std::string Attack::decifrar (std::string& s, std::string& clave){
    std::string ans="";
    for (int i=0;i<s.size();++i){
        ans+= ((s[i]-'A')-(clave[i%clave.size()]-'A')+26 )%26 +'A';
    }
    return ans;
}

std::vector< double> Attack::geth0 (std::vector<double>& v){
    std::vector<double> ans(v.size());
    std::vector<std::pair<double,int> > sorted;
    for (int i=0;i<v.size();++i)sorted.push_back({v[i],i});
    sort(sorted.begin(), sorted.end());
    for (int i=0;i<v.size();i++){
        ans[sorted[i].second]=std::min (i==0?1000:sorted[i].first-sorted[i-1].first,i==v.size()-1?1000:sorted[i+1].first-sorted[i].first);
    }
    return ans;
}

int Attack::test (){
    freopen ("input.txt", "r" , stdin);
    std::string s;
    std::cin>>s;
    std::cout<<"inidices de coincidencia para algunos tamaños: \n";
    for (int i=1;i<7;++i){
        std::vector<double> v = indiceCoincidenciaTamanoClave(s, i);
        double x=0;
        std::cout<<i<<", ";
        for (auto& c : v){
            x+=c;
        }
        std::cout <<x/(double)i<<" : ";
        for (auto& c: v)std::cout<<c<<' ';
        std::cout<<'\n';
    }
    std::cout<<"Indices de coincidencia asumiendo determinada letra:(tamano fijo 5) \n";
    int x=5;
    std::string abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    std::vector<std::vector<double> > m = indiceEsperadoTamanoClave(s, x);
    std::string key ="";
    for (int i=0;i<m.size();++i){
        std::cout <<i<<':';
        double mx =0 ;
        int idx=0;
        for (int j=0;j<m[i].size() ;++j){
            std::cout<<abc[j]<<' '<<m[i][j]<<' ';
            if (m[i][j]>mx){
                mx=m[i][j];
                idx=j;
            }
        }
        key+= abc[idx];
        std::cout<<std::endl;
    }
    std::cout<<"keyword="<<key<<std::endl<<"Analisis topologico de datos: \n";
    for(int i=0;i<x;i++){
        std::vector<double> h= geth0(m[i]);
        for (int i=0;i<h.size();++i){
            std::cout<<std::fixed<<std::setprecision(3);
            std::cout<<abc[i]<<' ' <<h[i]<<' ';
        }
        std::cout<<std::endl;
    }
    std::cout<<"texto claro: " <<decifrar(s,key);
}
