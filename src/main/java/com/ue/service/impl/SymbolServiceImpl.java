package com.ue.service.impl;

import com.ue.dao.SymbolMapper;
import com.ue.pojo.Symbol;
import com.ue.service.SymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service("symbolService")
public class SymbolServiceImpl implements SymbolService {

    @Autowired
    private SymbolMapper symbolMapper;

    @Override
    public Symbol findSymbol(Integer userId, Integer articleId, Integer sign) {
        Condition condition = new Condition(Symbol.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("articleId", articleId);
        criteria.andEqualTo("sign", sign);
        List<Symbol> symbols = symbolMapper.selectByExample(condition);
        return symbols.size() == 1 ? symbols.get(0) : null;
    }

    @Override
    public void removeSymbol(Integer symbolId) {
        symbolMapper.deleteByPrimaryKey(symbolId);
    }

    @Override
    public void saveSymbol(Symbol symbol) {
        symbolMapper.insert(symbol);
    }

    @Override
    public Integer countSymbol(Integer articleId, Integer sign) {
        Condition condition = new Condition(Symbol.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("articleId", articleId);
        criteria.andEqualTo("sign", sign);
        int num = symbolMapper.selectCountByExample(condition);
        return num;
    }
}
