package com.andreiruse.jdmengine.runtime.config

import com.andreiruse.jdmengine.exceptions.JdmEngineRuntimeException
import org.apache.commons.configuration.BaseConfiguration
import org.apache.commons.configuration.Configuration
import org.apache.commons.configuration.ConfigurationException
import org.apache.commons.configuration.PropertiesConfiguration
import org.slf4j.LoggerFactory

class EngineConfig(private val environment: String?) {
    private var configuration: Configuration = BaseConfiguration();

    init {
        this.loadConfig()
    }

    private fun loadConfig() {
        try {
            configuration = PropertiesConfiguration("config/$environment.config")
        } catch (e: ConfigurationException) {
            logger.error("Could not load configuration")
            throw JdmEngineRuntimeException("Error loading configuration for environment " + environment, e)
        }

        logger.info("Loaded config file")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(EngineConfig::class.java)
    }
}
