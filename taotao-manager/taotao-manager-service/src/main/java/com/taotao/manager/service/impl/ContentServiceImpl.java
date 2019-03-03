package com.taotao.manager.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.utils.EasyUIResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.manager.dao.mapper.TbContentDAO;
import com.taotao.manager.pojo.domain.TbContent;
import com.taotao.manager.pojo.domain.TbContentExample;
import com.taotao.manager.pojo.qo.ContentQo;
import com.taotao.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentDAO contentDAO;

    @Override
    @CacheEvict(cacheNames = "contentList")
    public TaotaoResult save(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentDAO.insertSelective(content);
        return TaotaoResult.ok();
    }

    @Override
    @Cacheable(cacheNames = "contentList")
    public String getAdItemList() {
        TbContentExample e = new TbContentExample();
        List<TbContent> tbContents = contentDAO.selectByExampleWithBLOBs(e);
        return JSONObject.toJSONString(tbContents);
    }

    @Override
    @Cacheable(cacheNames = "contentList")
    public EasyUIResult getContentList(ContentQo query) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        Long categoryId = query.getCategoryId();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContents = contentDAO.selectByExample(example);
        //PageInfo的参数必须为Page类型
//        Page page = new Page();
//        page.addAll(tbContents);
//		PageHelper.startPage(query.getPage(),query.getRows());
//        PageInfo<TbContent> pageInfo = new PageInfo<>(page);
        EasyUIResult result = new EasyUIResult(tbContents.size(), tbContents);
        return result;
    }

    @Override
    public TaotaoResult delete(ContentQo query) {
        List<Long> ids = query.getIds();
        try {
            for (Long id : ids) {
                contentDAO.deleteByPrimaryKey(id);
            }
            return TaotaoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.fail(e.getMessage());
        }
    }

}
