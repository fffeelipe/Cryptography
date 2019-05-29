#ifndef TEXTOCLARO_H
#define TEXTOCLARO_H

#include <QDialog>

namespace Ui {
class TextoClaro;
}

class TextoClaro : public QDialog
{
    Q_OBJECT

public:
    explicit TextoClaro(QWidget *parent = nullptr,std::string key="" , std::string s = "");
    ~TextoClaro();

private:
    Ui::TextoClaro *ui;
};

#endif // TEXTOCLARO_H
