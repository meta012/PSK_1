package lt.vu.mybatis.dao;

import lt.vu.mybatis.model.Horse;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface HorseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.HORSE
     *
     * @mbg.generated Thu May 13 23:13:40 EEST 2021
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.HORSE
     *
     * @mbg.generated Thu May 13 23:13:40 EEST 2021
     */
    int insert(Horse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.HORSE
     *
     * @mbg.generated Thu May 13 23:13:40 EEST 2021
     */
    Horse selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.HORSE
     *
     * @mbg.generated Thu May 13 23:13:40 EEST 2021
     */
    List<Horse> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.HORSE
     *
     * @mbg.generated Thu May 13 23:13:40 EEST 2021
     */
    int updateByPrimaryKey(Horse record);
}