#include "textoclaro.h"
#include "ui_textoclaro.h"

TextoClaro::TextoClaro(QWidget *parent, std:: string key, std:: string s) :
    QDialog(parent),
    ui(new Ui::TextoClaro)
{
    ui->setupUi(this);
    ui->plainTextEdit->setPlainText(QString::fromStdString(s));
    ui->plainTextEdit->setReadOnly(true);
    std::string temp = "llave: "+ key;
    ui->label->setText(QString::fromStdString(temp));
    this->setWindowTitle("Texto Claro");
}

TextoClaro::~TextoClaro()
{
    delete ui;
}
