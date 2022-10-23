package nuc.zm.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoMapper {
    Long query(String name);
}
