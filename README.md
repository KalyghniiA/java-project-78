### Hexlet tests and linter status:
[![hexlet-check](https://github.com/KalyghniiA/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/KalyghniiA/java-project-78/actions/workflows/hexlet-check.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/54fcc0806db08eec4502/maintainability)](https://codeclimate.com/github/KalyghniiA/java-project-78/maintainability)


Пакет для проверки валидации данных

## Validator

```sh
new Validator();
```

Класс для начала работы с валидатором. Имеет методы для работы с Integer, String, Map.

## String
Для валидации строк требуется вызвать метод '.string()' у объекта класса Validator.

```sh
Validator v = new Validator();
BaseSchema schema = v.string();
```

Основной метод для валидации - isValue(value). Данный метод принимает аргументом строку, которую требуется проверить по заданным параметрам и возвращает булево значение

```sh
schema.isValue("string");
```

Данная схема имеет параметры:

1. required() - параметр, указывающий обязательство передавать аргументом в метод isValue только не пустые строки

```sh
Validator v = new Validator();

StringSchema schema = v.string();

schema.isValid(""); // true
// Пока на вызван метод required(), null считается валидным
schema.isValid(null); // true

schema.required();

schema.isValid("what does the fox say"); // true
schema.isValid("hexlet"); // true
schema.isValid(null); // false
schema.isValid(5); // false
schema.isValid(""); // false
```

2. minLength(int) - параметр, указывающий минимальное количество символов в строке, передаваемой аргументом в метод isValue

```sh
Validator v = new Validator();

StringSchema schema = v.string();

schema.minLength(3);

schema.isValid("ha") //false;
schema.isValid("haha") //true;
```

3. contains(string) - параметр, который добавляет проверку на содержание строки, переданной аргументом в метод isValue, строки указанной параметром в метод contains()

```sh
Validator v = new Validator();

StringSchema schema = v.string();

schema.contains("wh").isValid("what does the fox say"); // true
schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false

schema.isValid("what does the fox say"); // false
// уже false, так как добавлена ещё одна проверка contains("whatthe")
```


## Number

Для валидации чисел, требуется вызвать метод '.number()' у объекта класса Validator.

```sh
Validator v = new Validator();
BaseSchema schema = v.number();
```

Основной метод для валидации - isValue(value). Данный метод принимает аргументом число, которое требуется проверить по заданным параметрам и возвращает булево значение

```sh
schema.isValue(value)
```

Данная схема имеет параметры:

1. required() - параметр, указывающий обязательство передавать аргументом в метод isValue только числа

```sh
Validator v = new Validator();

NumberSchema schema = v.number();

// Пока на вызван метод required(), null считается валидным
schema.isValid(null); // true
schema.required();

schema.isValid(null); // false
schema.isValid(10) // true
schema.isValid("5"); // false
schema.isValid(-10); // true
```

2. positive() - параметр, указывающий обязательство передавать только положительные числа

```sh
Validator v = new Validator();

NumberSchema schema = v.number();

schema.positive().isValid(null); // true
schema.required();

schema.isValid(10) // true
schema.isValid("5"); // false
schema.isValid(-10); // false
//  Ноль - не положительное число
schema.isValid(0); // false
```

3. range(minRange, maxRange) - параметр, задающий диапазон, в который должны попадать числа включая границы

```sh
Validator v = new Validator();

NumberSchema schema = v.number();

schema.range(5, 10);

schema.isValid(5); // true
schema.isValid(10); // true
schema.isValid(4); // false
schema.isValid(11); // false
```

## Map

Для валидации Map, требуется вызвать метод '.map()' у объекта класса Validator.

```sh
Validator v = new Validator();
BaseSchema schema = v.number();
```

Основной метод для валидации - isValue(value). Данный метод принимает аргументом Map<?, ?>, которую требуется проверить по заданным параметрам и возвращает булево значение

```sh
schema.isValue(value)
```

Данная схема имеет параметры:

1. required() - параметр, указывающий обязательство передавать аргументом в метод isValue только Map<?, ?>

```sh
Validator v = new Validator();

MapSchema schema = v.map();

schema.isValid(null); // true

schema.required();

schema.isValid(null) // false
schema.isValid(new HashMap()); // true
Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(data); // true
```

2. sizeof(number) - параметр, указывающий обязательное количество пар ключ-значение в объекте Map, переданным в метод isValue

```sh
Validator v = new Validator();

MapSchema schema = v.map();

Map<String, String> data = new HashMap<>();
data.put("key1", "value1");

schema.isValid(data); // true

schema.sizeof(2);

schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true
```

3. shape(schemas) - позволяет описывать валидацию для значений объекта Map по ключам.

```sh
Validator v = new Validator();

MapSchema schema = v.map();

Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());
schema.shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "Maya");
human2.put("age", null);
schema.isValid(human2); // true

Map<String, Object> human3 = new HashMap<>();
human3.put("name", "");
human3.put("age", null);
schema.isValid(human3); // false

Map<String, Object> human4 = new HashMap<>();
human4.put("name", "Valya");
human4.put("age", -5);
schema.isValid(human4); // false
```


Данный репозиторий создан в рамках учебного проекта образовательного ресурса Hexlet. [Прочитать больше о Hexlet](https://hexlet.io/?utm_source=github&utm_medium=link&utm_campaign=java-package).