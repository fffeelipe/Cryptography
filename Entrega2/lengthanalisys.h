#ifndef LENGTHANALISYS_H
#define LENGTHANALISYS_H

#include <QDialog>

namespace Ui {
class LengthAnalisys;
}

class LengthAnalisys : public QDialog
{
    Q_OBJECT

public:
    std::string s;
    LengthAnalisys(QWidget *parent ,std::string s);
    ~LengthAnalisys();

private slots:
    void on_pushButton_clicked();

private:
    Ui::LengthAnalisys *ui;
};

#endif // LENGTHANALISYS_H
