#language:ru
#encoding:UTF-8

@test
Функционал: Выход пользователя из сайта saucedemo.com
  Сценарий: Проверка выхода авторизованного пользователя из системы
    Допустим авторизованный пользователь находится на странице "https://www.saucedemo.com/inventory.html"
    Когда пользователь нажимает на всплывающее меню "Open Menu"
    И в меню выбирает ссылку "Logout"
    Тогда открывается страница входа "https://www.saucedemo.com/"