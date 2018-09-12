package com.serviceinfotech.logger.springlogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AmigoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public String callAmigo(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        logger.info("Call amigo with name : {}" ,name);
        stringBuilder.append("Hello ");
        stringBuilder.append(name);
        stringBuilder.append("!!!");
        return stringBuilder.toString();
    }
}
