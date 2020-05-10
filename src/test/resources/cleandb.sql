delete from test_brew_book.user;
insert into test_brew_book.user values (1, 'test', 'test', 'test@test.com'), (2, 'tester', 'tester', 'tester@test.com');

delete from test_brew_book.yeast;
insert into test_brew_book.yeast values (1, 'Wyeast', 'Belgian yeast');

delete from test_brew_book.style;
insert into test_brew_book.style values (1, 'Farmhouse Ale');

delete from test_brew_book.fermentable;
insert into test_brew_book.fermentable values (1, 'American Wheat');

delete from test_brew_book.hop;
insert into test_brew_book.hop values (1, 'Citra');

delete from test_brew_book.misc;
insert into test_brew_book.misc values (1, 'Cranberries');

delete from test_brew_book.role;
insert into test_brew_book.role values (1, 'registered_user', 'test', 1);

delete from test_brew_book.brew;
insert into test_brew_book.brew values (1, 'Spotted Cow', 'A refreshingly simple and easy-to-drink farmhouse ale', 'madison tap water', 1, 'pitched at 75C', 1, 1);

delete from test_brew_book.brew_misc;
insert into test_brew_book.brew_misc values (1, 1, 12.0, 'lb', 'fermentation', 24, 'hr', 1);

delete from test_brew_book.brew_hop;
insert into test_brew_book.brew_hop values (1, 1, 21.0, 'boil', 12, 'min', 'oz', 1);

delete from test_brew_book.brew_fermentable;
insert into test_brew_book.brew_fermentable values (1, 1, 10.0, 'lb', 1);