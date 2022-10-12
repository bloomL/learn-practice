# 味道与启发
1. 注释
   - 不恰当的信息
   - 废弃的注释
   - 冗余注释
   - 糟糕的注释
   - 注释掉的代码
2. 环境
3. 函数
   - 过多的参数（建议三个及以下）
   - 输出参数
   - 标识参数（消灭布尔值参数）
   - 死函数
4. 一般性问题
   - 一个源文件中存在多种语言
   - 明显的行为未被实现
   - 不正确的边界行为
   - 忽视安全
   - 重复
   - 在错误的抽象层级上的代码
   - 基类依赖于派生类
   - 信息过多
   - 死代码
   - 垂直分隔
   - 前后不一致
   - 混肴视听
   - 人为耦合
   - 特性依恋
   - 选择算子参数
   - 晦涩的意图
   - 位置错误的权责
   - 不恰当的静态方法
   - 使用解释性变量
   - 函数名称应该表达其行为
   - `理解算法`
   - 把逻辑依赖改为物理依赖
   - 用多肽替代If/Else或Switch/Case
   - 遵循标准约定
   - 用命名常量替代魔术数
   - 准确
   - 结构甚于约定
   - 封装条件
   - 避免否定性条件
   - 函数只该做一件事
   - 掩蔽时序耦合
   - 别随意
   - 封装边界条件
   - 函数应该只在一个抽象层级上
   - 在较高层级放置可配置数据
   - 避免传递浏览
5. Java
   - 通过使用通配符避免过长的导入清单
   - 不要继承变量
   - 常量 VS 枚举
6. 名称
   - 采用描述性名称
   - 名称应与抽象层级相符
   - 尽可能使用标准命名法
   - 无歧义的名称
   - 为较大作用范围选用较长名称
   - 避免编码
   - 名称应该说明副作用
7. 测试