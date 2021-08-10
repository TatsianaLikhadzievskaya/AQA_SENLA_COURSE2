#language:ru
#encoding:UTF-8

@test
Функционал: Успешный вход пользователя на сайт saucedemo.com
  Сценарий: Проверка авторизации пользователя при вводе логина и пароля
    Допустим открыта страница "https://www.saucedemo.com/"
    Когда в поле логин введено значение "standard_user"
    И в поле пароль введено значение "secret_sauce"
    И нажата кнопка "Login"
    Тогда выполнен переход на страницу "https://www.saucedemo.com/inventory.html"


