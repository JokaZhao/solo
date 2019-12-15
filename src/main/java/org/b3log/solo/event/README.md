### Latke都事件注册方式

Latke中全局的事件通过EventManager来统一管理，其实就是一个工厂模式。
实现一个事件只需要继承AbstractEventListener抽象类，这里需要自己实现两个抽象方法，一个是用来标示事件的名称，一个是具体的实现类。
在使用的时候，需要通过定义的名称，通过EventManager来调用

总共分三步：

1. 定义一个事件，继承AbstractEventListener类
2. 将事件注册到EventManager，调用EventManager.registerListener方法
3. 在执行某个步骤的时候触发事件，EventManager.fireEventAsynchronously