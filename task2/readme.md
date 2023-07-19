## Урок 3. Механизмы контрольных групп
----
### Задание 1

1.  Запустить контейнер с ubuntu, используя механизм LXC.
    * Смотрим список контейнеров: **lxc-ls –f**

        ![](images/image1.png)
    
    * Запускаем контейнер task2-1: **lxc-start -d -n task2-1**
        
        ![](images/image2.png)

2.	Ограничить контейнер 256 Мб ОЗУ и проверить, что ограничение работает.
    *   Открываем конфиг контейнера: **nano /var/lib/lxc/task2-1/config**

        ![](images/image3.png)

    *   Устанавливаем ограничение по памяти:  **lxc.cgroup2.memory.max = 256M**

        ![](images/image4.png)

    *   Перезапускаем контейнер, подключаемся к нему и смотрим количество выделенной памяти:
        *   **lxc-stop -n task2-1**
        *   **lxc-start -d -n task2-1**
        *   **lxc-attach -n task2-1**
        *   **free -m**

        ![](images/image5.png)

3.	Добавить автозапуск контейнеру, перезагрузить ОС и убедиться, что контейнер действительно запустился самостоятельно.
    *   Открываем конфиг контейнера: **nano /var/lib/lxc/task2-1/config**
    *   Устанавливаем автозапуск: **lxc.start.auto = 1**

        ![](images/image6.png)

    *   Перезапускаем контейнер и смотрим параметр автозапуска в списке контейнеров:
        *   **lxc-stop -n task2-1**
        *   **lxc-start -d -n task2-1**
        *	**lxc-ls -f**

        ![](images/image7.png)

    *	Останавливаем контейнер и перезагружаем Ubuntu

        ![](images/image8.png)

    *   После перезагрузки убеждаемся, что контейнер запустился самостоятельно

        ![](images/image9.png)

4.	При создании (запуске) указать файл, куда записывать логи:
    *	**lxc-start --logfile=/var/log/lxc.log -d -n task2-1**

        ![](images/image10.png)

5.	После перезагрузки проанализировать логи:
    *	Была попытка запустить уже запущенный контейнер
    
        ![](images/image11.png)

### Задание 2
Настроить автоматическую маршрутизацию между контейнерами. Адреса можно взять: 10.0.12.0/24 и 10.0.13.0/24.

1.	Создаем сетевой интерфейс типа bridge.
    *	**nano /etc/netplan/00-installer-config.yaml**

        ![](images/image12.png)

    *	**netplan apply**

        ![](images/image13.png)

2.	Создадим контейнеры.
    *	**lxc-create -n task2-1 -t ubuntu**
    *	**lxc-create -n task2-2 -t ubuntu**

        ![](images/image14.png)

3.	Сконфигурируем контейнер task2-1
    *	**nano /var/lib/lxc/task2-1/config**
        
        ![](images/image15.png)

4.	Сконфигурируем контейнер task2-2
    *	**nano /var/lib/lxc/task2-2/config**

        ![](images/image16.png)

5.	Запустим контейнеры
    *	**lxc-start -d -n task2-1**
    *	**lxc-start -d -n task2-2**

        ![](images/image17.png)

6.	Проверяем 
    *	**lxc-attach -n task2-1**

        ![](images/image18.png)

    *	**lxc-attach -n task2-2**

        ![](images/image19.png)