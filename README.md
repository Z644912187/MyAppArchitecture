#开发前须知
工程目录主体分为2个module，分别是 app 和 corelib 
* corelib中包括所有跟业务无关的组件：可以独立的UI，工具类utils, 要继承的父类TActivity等。
* app中都是直接的业务模块如 登录，组队等

###
#包结构(app module下)
```
-core(存放全局缓存，全局http，全局mvp基类)  
  -cache(全局缓存)
  -http (全局http)
  -mvp  (mvp相关基类)
  -application
-login(业务模块-登录，其他业务模块类似划分)
  -bean(实体类)
  -http(跟http即网络请求相关的)
  -dao(数据库增删改查)
  -service(复杂业务可以加一层service，抽出presenter中的复杂逻辑)
  -mvp(存放mvp相关的类，如LoginActivity, LoginContract, LoginPresenter...)
-main(业务模块-主页，其他业务模块类似划分)
  -bean(实体类)
  -http(跟http即网络请求相关的)
  -dao(数据库增删改查)
  -service(复杂业务可以加一层service，抽出presenter中的复杂逻辑)
  -mvp(存放mvp相关的类，如MainActivity, MainContract, MainPresenter...)
-walllet(业务模块-钱包，其他业务模块类似划分)
  -bean(实体类)
  -http(跟http即网络请求相关的)
  -dao(数据库增删改查)
  -service(复杂业务可以加一层service，抽出presenter中的复杂逻辑)
  -mvp(存放mvp相关的类，如wallletActivity, wallletContract, wallletPresenter...)
...(其他业务模块的包划分类似)
```
***


#关于MVP
采用Google最新的MVP模型，可以参考工程下main目录中的结构，详细了解：http://www.jianshu.com/p/dc9733bc3a54

#关于网络框架：Retrofit2.0
可以参考：http://www.jianshu.com/p/e438594d9c93

#图片加载框架：glide 可以考虑 fresco

#butterknife：可以借助插件一键绑定UI

#关于日志
LogUtil.info("TAG", "INFO");
一般信息打印用info，异常用error

#MVP开发模板
对于不熟悉MVP的小伙伴们可以参考 “钱包界面WalletActivity”，这部分通过比较详细代码展现MVP的实现"# MyAppArchitecture" 

#关于支持手势滑动退出
Activity动画实现
<p>第一步：新增Activity必须继承 com.corelib.base.ui.SwipeBackActivity</p>
<p>第二步：AndroidManifest 注册 Activity时，声明主题theme如下：</p>
<pre><code>
<activity
    android:name=".xxxActivity"
    android:screenOrientation="portrait"
    android:theme="@style/AppThemeCustomAnimation" />
</code></pre>
<p>第三步：覆盖SwipeBackActivity中的isSupportSwipeBack()方法，该方法返回true，表示支持向右滑动推出activity；否则不支持该效果</p>
<pre><code>
@Override
protected boolean isSupportSwipeBack() {
    return true;
}
</code></pre>
<p>注：关于手势触法区域大小，可通过更改mValidDragigit大小</p>
<pre><code>
public class PagerEnabledSlidingPaneLayout extends SlidingPaneLayout {
    /**
     * 手势滑动退出的有效相应区域
     */
    private int mValidDragArea;
    private float mValidDragigit = 25;
。。。。
}
</code></pre>