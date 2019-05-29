#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <bits/stdc++.h>
#include <lengthanalisys.h>
#include "attack.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
}

MainWindow::~MainWindow()
{
    delete ui;
}

std::string MainWindow::getText(){
    return ui->texto->toPlainText().toUtf8().constData();
}

void MainWindow::on_pushButton_clicked(){
    LengthAnalisys w(this,getText());
    w.exec();
}
