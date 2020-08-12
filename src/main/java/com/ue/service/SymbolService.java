package com.ue.service;

import com.ue.pojo.Symbol;

public interface SymbolService {
    Symbol findSymbol(Integer userId, Integer articleId, Integer sign);

    void removeSymbol(Integer symbolId);

    void saveSymbol(Symbol symbol);

    Integer countSymbol(Integer articleId, Integer sign);
}
