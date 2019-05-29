#ifndef ATTACK_H
#define ATTACK_H

#include<bits/stdc++.h>
const int TAM=26;
const double ind = 0.0656010;

class Attack
{
public:
    Attack();
    std::vector<int> getFrecuencia(std::string &s);
    double indiceCoincidenciaFrecuencias(std::vector<int> frecuencias);
    double indiceCoincidencia ( std::string& s );
    std::vector<double> indiceCoincidenciaTamanoClave ( std::string& s , const int& n);
    std::vector<std::vector<double> > indiceEsperadoTamanoClave (std::string& s,  int& n);
    std::string decifrar (std::string& s, std::string& clave);
    std::vector< double> geth0 (std::vector<double>& v);
    int test();
};
#endif // ATTACK_H
