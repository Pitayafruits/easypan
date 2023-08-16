package com.cc.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.List;

interface BaseMapper<T,P> {
    /**
     * insert:(插入)
     */
    Integer insert(@Param("bean") T t);
    /**
     * insertOrUpdate:(插入或更新)
     */
    Integer insertOrUpdate(@Param("bean") T t);
    /**
     * insertBatch:(批量插入)
     */
    Integer insertBatch(@Param("list") List<T> t);
    /**
     * insertOrUpdateBatch:(批量插入或更新)
     */
    Integer insertOrUpdateBatch(@Param("list") List<T> list);
    /**
     * selectList:(根据参数查询集合)
     */
    List<T> selectList(@Param("query") P p);
    /**
     * selectCount:(根据参数查询数量)
     */
    Integer selectCount(@Param("query") P p);

}
