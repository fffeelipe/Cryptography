#include "lengthanalisys.h"
#include "ui_lengthanalisys.h"
#include "attack.h"
#include <QtCharts>
#include "textoclaro.h"
LengthAnalisys::LengthAnalisys(QWidget *parent, std::string s):
    QDialog(parent),
    ui(new Ui::LengthAnalisys)
{
    this->s = s;
    ui->setupUi(this);
    Attack at;
    QStringList titles;
    titles<<"longitud"<<"Índice de coincidencia";
    ui->tableWidget->setColumnCount(2);
    ui->tableWidget->setHorizontalHeaderLabels(titles);
    ui->tableWidget->horizontalHeader()->setStretchLastSection(true);
    for(int i = 0 ; i<10; i++){
        std::vector<double> v = at.indiceCoincidenciaTamanoClave(s, i+1);
        ui->tableWidget->insertRow(ui->tableWidget->rowCount());
        ui->tableWidget->setItem(i,0,new QTableWidgetItem(QString::number(i+1)));
        ui->tableWidget->setItem(i,1,new QTableWidgetItem(QString::number((std::accumulate(v.begin(), v.end(), 0.0))/(v.size()))));
    }

}

LengthAnalisys::~LengthAnalisys()
{
    delete ui;
}

void LengthAnalisys::on_pushButton_clicked(){
    if(ui->len->value() == 0){
        QMessageBox msgBox;
        msgBox.setText("Longitud inválida");
        msgBox.setInformativeText("La longitud de la clave debe ser al menos 1");
        msgBox.exec();
        return;
    }
    QT_CHARTS_USE_NAMESPACE
    std::string abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    Attack at;
    int x = ui->len->value();
    std::vector<std::vector<double> > m = at.indiceEsperadoTamanoClave(s, x);
    std::string key ="";

    for (int i=0;i<m.size();++i){
            //cout <<i<<':';
            double mx =0 ;
            int idx=0;
            std::cout<<m[i].size();
            QBarSet *set[m[i].size()];
            for (int j=0;j<m[i].size() ;++j){
                //cout<<abc[j]<<' '<<m[i][j]<<' ';
                set[j] = new QBarSet(QString::fromStdString(abc.substr(j,1)));
                *set[j] << m[i][j];
                if (m[i][j]>mx){
                    mx=m[i][j];
                    idx=j;
                }
            }
            key+= abc[idx];


        QBarSeries *series = new QBarSeries();
        for(int i = 0; i<26; i++)
            series->append(set[i]);


        QChart *chart = new QChart();
        chart->addSeries(series);
        std::string temp1 = "Indices de coincidencia para letras en posición:" + std::to_string(i);
        chart->setTitle(QString::fromStdString(temp1));
        chart->setAnimationOptions(QChart::SeriesAnimations);

        chart->legend()->setVisible(true);
        chart->legend()->setAlignment(Qt::AlignBottom);

        QChartView *chartView = new QChartView(chart);
        chartView->setRenderHint(QPainter::Antialiasing);

        QMainWindow *window = new QMainWindow;
        window->setCentralWidget(chartView);
        window->resize(420, 300);
        window ->setAttribute(Qt::WA_DeleteOnClose);
        window->show();
    }

    TextoClaro *tx = new TextoClaro(nullptr,key, at.decifrar(s,key));
    tx->setAttribute(Qt::WA_DeleteOnClose);
    tx->show();




}
