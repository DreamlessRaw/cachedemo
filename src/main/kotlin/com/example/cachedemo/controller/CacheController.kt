package com.example.cachedemo.controller

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.example.cachedemo.entity.Account
import com.example.cachedemo.mapper.AccountMapper
import com.example.cachedemo.mapper.AccountNoMapper
import com.example.cachedemo.services.CacheUtils
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.annotation.Resource

@RestController
@RequestMapping("cache")
class CacheController {
    @Resource
    lateinit var accountMapper: AccountMapper
    @Resource
    lateinit var accountNoMapper: AccountNoMapper

    @Resource
    lateinit var cacheUtils: CacheUtils

    @PostMapping("get")
    fun get(@RequestBody page: Page<Account>): IPage<Account> {
        accountNoMapper.selectPage(Page(1, 10), null)
        return cacheUtils.test(page)
    }

    @Cacheable(cacheNames = ["account"], key = "#page.current")
    fun test(page: Page<Account>): IPage<Account> {
        return accountMapper.selectPage(page, null)
    }

    @PostMapping("set")
    @CacheEvict(cacheNames = ["account"], allEntries = true, beforeInvocation = true)
    fun set(@RequestBody account: Account): Boolean {
        account.createTime = Date()
        return accountMapper.insert(account) > 0
    }

    @PostMapping("update")
    @CachePut(cacheNames = ["account"])
    fun update(@RequestBody account: Account): Boolean {
        return accountMapper.updateById(account) > 0
    }
}
