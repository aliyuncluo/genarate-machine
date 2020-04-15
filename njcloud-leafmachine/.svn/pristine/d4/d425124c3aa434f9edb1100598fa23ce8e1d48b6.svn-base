package com.najie.exam.service;

import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.druid.pool.DruidDataSource;
import com.najie.exam.Constants;
import com.najie.exam.IDGen;
import com.najie.exam.common.PropertyFactory;
import com.najie.exam.common.Result;
import com.najie.exam.common.ZeroIDGen;
import com.najie.exam.exception.InitException;
import com.najie.exam.segment.SegmentIDGenImpl;
import com.najie.exam.segment.dao.IDAllocDao;
import com.najie.exam.segment.dao.impl.IDAllocDaoImpl;

@Service("SegmentService")
public class SegmentService {
    private Logger logger = LoggerFactory.getLogger(SegmentService.class);

    private IDGen idGen;
    private DruidDataSource dataSource;

    public SegmentService() throws SQLException, InitException {
        Properties properties = PropertyFactory.getProperties();
        boolean flag = Boolean.parseBoolean(properties.getProperty(Constants.LEAF_SEGMENT_ENABLE, "true"));
        if (flag) {
            // Config dataSource
            dataSource = new DruidDataSource();
            dataSource.setUrl(properties.getProperty(Constants.LEAF_JDBC_URL));
            dataSource.setUsername(properties.getProperty(Constants.LEAF_JDBC_USERNAME));
            dataSource.setPassword(properties.getProperty(Constants.LEAF_JDBC_PASSWORD));
            dataSource.init();

            // Config Dao
            IDAllocDao dao = new IDAllocDaoImpl(dataSource);

            // Config ID Gen
            idGen = new SegmentIDGenImpl();
            ((SegmentIDGenImpl) idGen).setDao(dao);
            if (idGen.init()) {
                logger.info("Segment Service Init Successfully");
            } else {
                throw new InitException("Segment Service Init Fail");
            }
        } else {
            idGen = new ZeroIDGen();
            logger.info("Zero ID Gen Service Init Successfully");
        }
    }

    public Result getId(String key) {
        return idGen.get(key);
    }

    public SegmentIDGenImpl getIdGen() {
        if (idGen instanceof SegmentIDGenImpl) {
            return (SegmentIDGenImpl) idGen;
        }
        return null;
    }
}
