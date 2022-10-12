# 简化条件逻辑
1. 分解条件表达式
   - 复杂条件表达式
2. 合并条件表达式
   - 条件各不相同，最终行为却一致
3. 以卫语句取代嵌套条件表达式
   - `卫语句`，某个条件极其罕见，就应该单独检查该条件，并在该条件为真时立刻从函数中返回
4. 以多肽取代条件表达式
5. 引入特例
6. 引入断言