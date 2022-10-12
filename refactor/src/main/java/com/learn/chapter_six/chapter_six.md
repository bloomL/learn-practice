# 第一组重构
1. 提炼方法
2. 内联方法
   - 去掉非必要间接层
   - 多个方法内联到达方法
3. 提炼变量
   - 将表达式分解为比较容易管理的形式
4. 内联变量（消除变量）
   - 名字并不比表达式本身更具表现力
   - 变量可能会妨碍重构附近的代码
5. 修改方法声明
6. 封装变量
7. 变量改名
8. 引入参数对象
9. 方法组合成类
   - 一组函数形影不离地操作同一块数据
10. 方法组合成变换（数据"喂"给程序，让它再计算出各种派生信息。 这些派生数值可能会在几个不同地方用到）
    - 采用数据变换函数
    - 函数组合成类(源数据创建一个类，再把相关的计算逻辑搬移到类中)
11. 拆分阶段
