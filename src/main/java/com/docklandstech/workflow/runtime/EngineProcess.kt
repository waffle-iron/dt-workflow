package com.docklandstech.workflow.runtime

import com.docklandstech.workflow.input.bpmnParser.BpmnParser
import com.docklandstech.workflow.runtime.config.EngineConfig
import org.apache.commons.cli.*
import org.slf4j.LoggerFactory
import java.nio.file.Paths
import java.util.*

class EngineProcess {

    fun run(config: EngineConfig, bpmnFileName: String) {
        val parser = BpmnParser()
        val graph = parser.parse(Paths.get(bpmnFileName))
    }

    companion object {
        private val logger = LoggerFactory.getLogger(EngineProcess::class.java)

        @JvmStatic fun main(args: Array<String>) {
            val process = EngineProcess()
            val bpmnNameOption = Option.builder().argName("BPMN file path").hasArg().longOpt("bpmn").required().build()
            val environmentOption = Option.builder().argName("Environment Name").hasArg().longOpt("env").required().build()
            val argumentValues = processArgs(args, Arrays.asList(environmentOption, bpmnNameOption))
            val engineConfig = EngineConfig(argumentValues[environmentOption.longOpt])
            val bpmnFileName = argumentValues[bpmnNameOption.longOpt]
            logger.info("Starting engine")
            process.run(engineConfig, bpmnFileName!!)
        }

        private fun processArgs(args: Array<String>, optionList: MutableList<Option>): Map<String, String> {
            val options = Options()
            for (option in optionList) {
                options.addOption(option)
            }
            val commandLine = extractCommandLineArgs(args, options)
            val argValues = HashMap<String, String>()
            for (option in optionList) {
                argValues.put(option.longOpt, commandLine.getOptionValue(option.longOpt))
            }
            return argValues
        }

        private fun extractCommandLineArgs(args: Array<String>, options: Options): CommandLine {
            val parser = DefaultParser()
            val commandLine: CommandLine
            try {
                commandLine = parser.parse(options, args)
            } catch (e: ParseException) {
                logger.error("Invalid command line parameters" + e.message)
                val helpFormatter = HelpFormatter()
                helpFormatter.printHelp(EngineProcess::class.java.name, options)
                throw e
            }
            return commandLine
        }
    }
}
