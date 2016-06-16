
前言
=======
之前版本项目结构是使用传统的MVC：https://github.com/122627018/TeachingOffice

由于最近的博文一直在讲抓包和MVP模式的东西，所以决定自己改一个写过的项目练练手，实现app登录校园网并对校园网数据进行抓取展示
软件截图
=============
![这里写图片描述](http://img.blog.csdn.net/20160524101415247)
![这里写图片描述](http://img.blog.csdn.net/20160524101430076)
![这里写图片描述](http://img.blog.csdn.net/20160524101444201)
包结构
==============
![这里写图片描述](http://img.blog.csdn.net/20160524091018314)
设计思路
=============
MVP模式
----------------------
模式是从传统的mvc模式改进为MVC模式，关于MVP模式的说明可以参考我之前写过的关于MVP模式的介绍
presenter:数据控制中心，负责为每个ui界面适配数据
model：负责调用各个服务进行数据适配
Serviceapi：服务，也就是负责底层的访问浏览器网页并解析的行为
ui：ui层

Model层
-----------------
model层分工比较明确，如下图：
![这里写图片描述](http://img.blog.csdn.net/20160524091720799)
引擎和服务组成了Model层，打个比方，例如说我要获取成绩，就必须要由引擎调用登录服务，然后再调用获取成绩服务，这样一来，每个模块各自都有各自要做的事，代码结构非常清晰，相对于单元测试来说也比较方便。

M与P的交互
--------------------
model与presenter之间的交互采用RxJava，可以让每一步的逻辑线性化从而更加清晰，还是拿上面的例子来说，登录后获取成绩，如果没有使用RxJava，可能就得陷入至少2此回调中。
上面都只是举个简单的例子，在实际应用中，远远比上面说的要难地多，可以看看下面的逻辑图：
![这里写图片描述](http://img.blog.csdn.net/20160524092719078)
不难看出，在这里***数据逻辑和ui逻辑多重交互***，如果只在一个Activity里面写这些逻辑，代码量和复杂程度可想而知，所以使用RxJava+MVP模式是一个很明智的选择，数据逻辑在P中写，ui逻辑在view中写，然后P与M的交互则使用RxJava让复杂的逻辑显得更加清晰

错误处理
--------------------------
在上面展示的逻辑图中还有一个很重要的点没提出来，就是当遇到错误发生时怎么办？在做app的 过程经常遇到的错误有：

 - 网页参数编码错误导致访问网页失败
 - 账号密码错误等
 - 网页服务器无响应
 - 客户端网络断开等
 
解决这些错误让lz头疼了好几天，因为view层与数据控制层的分开，所以你并不能在activity里面判断具体的错误原因然后作出响应。规范的做法是在presenter中判断错误，并控制view作出响应。所以这也是我在Presenter和Model层之间使用RxJava的一个重要原因，RxJava的一个重要特性就是如果在被观察者执行的过程中跑出异常，则会中断流程，直接调用观察者的onError函数，并传出异常；

```
 model.Login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Html_Main>() {
                    @Override
                    public void onCompleted() {
                        view.hideLoadingDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showErrorDialog(((MyException) e).getViewErrorInfo());
                    }

                    @Override
                    public void onNext(Html_Main html_main) {
                        Glo.html_main = html_main;
                        ConstantValue.isOfficeLogin = true;
                        view.hideLoadingDialog();
                        view.runMainAct();
                    }
                })
        ;
```
当然在onError里面要对Exception进行判断是哪种错误

blog：http://blog.csdn.net/qq122627018/article/details/51487411


