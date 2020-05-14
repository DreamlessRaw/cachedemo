package com.example.cachedemo.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.util.*


@TableName("account_no")
class AccountNo {
    @TableId(value = "id", type = IdType.AUTO)
    var id: Int = 0
    var code: String = ""
    var name: String = ""
    var createTime: Date? = null
}

