# Ссылки
H2 бд - http://localhost:8081/h2-console/  
swagger - http://localhost:8081/swagger-ui/index.html#/  
сайт для парсинга - https://irecommend.ru/  
# Ручки
Для ручек требуется полная ссылка или полное имя продукта например:
ссылка - https://irecommend.ru/content/kompyuternaya-programma-mobilnoe-prilozhenie-zolotoe-yabloko-gold-apple  
или
имя - Приложение Золотое яблоко  

## /collectors/collectIrecommend
по ссылке собирает отзывы со всех страниц товара  
использует ThreadPool для парсинга и Daemon поток для логирования, а также atomic int для подсчёта кол-ва отзывов

## /schedule/Start и /schedule/Stop
start начинает отслеживать товар и каждые 5 минут проверяет нужно ли делать новый collect, в случае изменений вызывет collct  
stop останавливает работу проверки  
используют Sheduler для проверки по рассписанию  
обе ручки работают от ссылки

## /reviews/Filtred
фильтрует отзывы с испольхованием parallelStream по рейтингу  
требует название товара и рейтинг от 1 до 5