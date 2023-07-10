## Задача 1 Необходимо продемонстрировать изоляцию одного и того же приложения (как решено на семинаре - командного интерпретатора) в различных пространствах имен.

1.	Проверяем интерфейсы в корне: **ip a**

![](image1.png)

2.	Создаем сетевое пространство имен:  

**sudo unshare --net /bin/bash**

![](image2.png)

**sudo ip netns add netnstask1**

![](image2_2.png)

3.	Проверяем: **sudo nsenter --net=/var/run/netns/netnstask1 /bin/bash**

![](image3.png)

4.	Создаем виртуальные интерфейсы: **sudo ip link add veth0-task1 type veth peer name ceth0-task1**

![](image4.png)

5.	Помещаем ceth0-task1 в netnstask1: **sudo ip link set ceth0-task1 netns netnstask1**

![](image5.png)

6.	Включаем интерфейс veth0-task1: sudo ip link set veth0-task1 up
Присваиваем ip: **sudo ip addr add 10.0.0.1/24 dev veth0-task1**

![](image6.png)

7.	Переходим внутрь netnstask1: **sudo nsenter --net=/var/run/netns/netnstask1 /bin/bash**

    Включаем интерфейс lo: **ip link set lo up**

    Включаем интерфейс ceth0-task1:  **ip link set ceth0-task1 up**

![](image7.png)

8.	Присваиваем ip : **ip addr add 10.0.0.2/24 dev ceth0-task1**

![](image8.png)

9.	Проверяем: **ping 10.0.0.2**

![](image9.png)

10.	Проверяем: ping 10.0.0.1

![](image10.png)