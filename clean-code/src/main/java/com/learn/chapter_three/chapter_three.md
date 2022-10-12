# 函数
1. 短小
2. 只做一件事
3. 每个函数一个抽象层级
4. switch语句
5. 使用描述性的名称
6. 函数参数
   - 零参数函数，单参数函数， 双参数函数；尽量避免三参数函数
   - 禁止标识参数（传入布尔值）
   - 需要三个或三个以上参数，其中一些参数应该`封装为类`
7. 无副作用
8. 分隔指令与询问
9. 使用异常替代返回错误码
   - 抽离Try/Catch代码块
   - 错误处理就是一件事
   - `Error.java`依赖磁铁（许多类都得导入和使用，当其修改时，所有其他的类需重新编译和部署）
10. 别重复自己
11. 结构化编程