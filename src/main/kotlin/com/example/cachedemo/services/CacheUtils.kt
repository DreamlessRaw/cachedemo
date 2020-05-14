package com.example.cachedemo.services

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.example.cachedemo.entity.Account
import com.example.cachedemo.mapper.AccountMapper
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import javax.annotation.Resource

@Service
class CacheUtils{

    @Resource
    lateinit var accountMapper: AccountMapper

    @Cacheable(cacheNames = ["account"], key = "#page.current")
    fun test(page: Page<Account>): IPage<Account> {
        return accountMapper.selectPage(page, null)
    }
}
