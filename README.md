# Locker

## 开发环境
 - JDK1.8+
 
## 业务需求

需求描述：储物柜(Locker)可以存包、取包
![locker](./locker.png)

评分标准：参考Classroom中的评分标准Excel文档

### 需求澄清总结：
1. 储物柜没有容量限制
2. 储物柜没有尺寸限制，默认多大的包都能存
3. 硬件系统功能不需要考虑（开门/关门/按钮/停电/没票纸）
4. 存包失败，需要提示用户是因为储物柜满了
5. 取包失败，需要提示用户是因为票据无效
6. 存包的位置是随机，没有顺序
7. 不要脑补需求，及时和PO确认
8. 不考虑并发


### lockerManager
1.Given 多个locker且未满和0个robot When LockerRobotManager存包 Then 返回小票
2.Given 多个locker且都满了和0个robot When LockerRobotManager存包 Then 提示无空间
3.Given 一个可以存储的Primary robot和一个可以存储的smart robot When LockerRobotManager存包  Then 返回小票
4.Given 一个不可以存储的Primary robot和一个可以存储的smart robot When LockerRobotManager存包 Then 返回小票
5.Given 一个不可以存储的Primary robot和一个不可以存储的smart robot,2个可以存储的locker When LockerRobotManager存包 Then 返回小票
6.Given 一个不可以存储的Primary robot和一个不可以存储的smart robot,2个不可以存储的locker When LockerRobotManager存包 Then 提示物空间
7.Given 合法的小票 When LockerRobotManager取包 Then 取包成功
8.Given 不合法的小票 When LockerRobotManager取包 Then 取包失败，提示不合法的票
