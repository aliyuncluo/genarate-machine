package com.najie.exam.service;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.najie.exam.Constants;
import com.najie.exam.IDGen;
import com.najie.exam.common.PropertyFactory;
import com.najie.exam.common.Result;
import com.najie.exam.common.ZeroIDGen;
import com.najie.exam.exception.InitException;
import com.najie.exam.snowflake.SnowflakeIDGenImpl;

@Service("SnowflakeService")
public class SnowflakeService {
    private Logger logger = LoggerFactory.getLogger(SnowflakeService.class);

    private IDGen idGen;

    public SnowflakeService() throws InitException {
        Properties properties = PropertyFactory.getProperties();
        boolean flag = Boolean.parseBoolean(properties.getProperty(Constants.LEAF_SNOWFLAKE_ENABLE, "true"));
        if (flag) {
            String zkAddress = properties.getProperty(Constants.LEAF_SNOWFLAKE_ZK_ADDRESS);
            int port = Integer.parseInt(properties.getProperty(Constants.LEAF_SNOWFLAKE_PORT));
            idGen = new SnowflakeIDGenImpl(zkAddress, port);
            if(idGen.init()) {
                logger.info("Snowflake Service Init Successfully");
            } else {
                throw new InitException("Snowflake Service Init Fail");
            }
        } else {
            idGen = new ZeroIDGen();
            logger.info("Zero ID Gen Service Init Successfully");
        }
    }

    public Result getId(String key) {
        return idGen.get(key);
    }
}
