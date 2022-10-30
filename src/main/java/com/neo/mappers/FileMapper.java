package com.neo.mappers;

import com.neo.common.vo.FileVO;
import com.neo.config.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("fileMapper")
public interface FileMapper {
    int fileInsert(FileVO fileVO) throws Exception;

}
