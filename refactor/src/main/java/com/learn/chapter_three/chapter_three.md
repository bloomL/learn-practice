# 代码的坏味道
1. 神秘命名
2. 重复代码
   - 同一个类，可`提炼函数`
   - 相似而非完全相同，`移动语句`，再把相似部分提炼
   - 同一个超类的不同子类，使`函数上移`
3. 过长函数
   - `提炼函数`
   - 大量参数和临时变量，`查询取代临时变量`来消除临时元素。 `引入参数对象`和`保持对象完整`可将长参数列表变得简洁
   - 如仍有大量临时变量和参数，`以命令取代函数`
   - `分解条件表达式`
   - 庞大的switch语句，每个分支通过`提炼函数`变成独立的函数调用
   - 多个switch语句基于同一个条件进行分支选择，`多肽取代条件表达式`
4. 过长参数列表
   - `查询取代参数`
   - `保持对象完整`
   - `引入参数对象`
   - `移除标记参数`
   - `函数组合成类`
5. 全局数据
   - `封装变量`
6. 可变数据
7. 发散式变化
   因为不同的原因在不同的方向上发生变化
8. 霰弹式修改
   每遇到某种变化，都必须在许多不同的类内做出许多小修改
   - `搬移函数`和`搬移字段`
   - `函数组合成类`
   - `拆分阶段`
   - 常用策略 `内联函数`，`内联类`
9. 依恋情节
   一个函数跟另一个模块中的函数或者数据交流格外频繁，远胜于在自己所处模块内部的交流
   - `搬移函数` `提炼函数`
10. 数据泥团
    多个类中相同的字段、许多函数签名中相同的参数 
    - `提炼类`到独立对象
    - `引入参数对象`或`保持对象完整`
11. 基本类型偏执
    大量使用基本类型，即整数、浮点数和字符串
    - `对象取代基本类型`
12. 重复的switch
    - `多肽取代条件表达式`
13. 循环语句
    - `管道取代循环`
14. 冗赘元素
15. 夸夸其谈通用性
    企图以各式各样的钩子和特殊情况来处理一些 非必要的事情
16. 临时字段
    内部某个字段仅为某种特定情况而设
    - `提炼类`，`搬移函数`把字段相关的代码放到新类
17. 过长的消息链
    用户向一个对象请求另一个对象，然后再向后者请求另一个对 象，然后再请求另一个对象
    - `隐藏委托关系`
18. 中间人
    某个类的接口有一半的函数都委 托给其他类，这属于过度运用
    - `移除中间人`
19. 内幕交易
    模块之间大量交换数据，必须尽量减少这种情况，并把这种交换都放到明面上来
    - `搬移函数`和`搬移字段`减少它们的私下交流；或者用`隐藏委托关系`，把另一个模块变成两者的中介
20. 过大的类
    - 提炼类
    - 提炼超类
    - 子类取代类型码
21. 异曲同工的类
22. 纯数据类
23. 被拒绝的遗赠
    子类应该继承超类的函数和数据。但如果它们不想或不需要继承，又该怎么办呢？它们得到所有礼物，却只从中挑选几样来玩
24. 注释
    - 注释之所以存在乃是因为代码很糟糕，注释可以带我们找到需要重构的地方





