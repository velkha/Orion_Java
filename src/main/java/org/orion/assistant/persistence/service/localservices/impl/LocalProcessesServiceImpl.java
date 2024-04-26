package org.orion.assistant.persistence.service.localservices.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.orion.assistant.exception.custom.ProcessExecutionException;
import org.orion.assistant.integration.ProcessExecutor;
import org.orion.assistant.persistence.service.localservices.LocalProcessesService;
import org.springframework.stereotype.Service;

@Service
public class LocalProcessesServiceImpl implements LocalProcessesService{
    private static Logger LOG = LogManager.getLogger(LocalProcessesServiceImpl.class);

    public String callLocal(Map<String, String> parameters, String command) {
        String rtrJson = null;
        try {
            rtrJson = processExecutor.callLocal(parameters, command);
        } catch (ProcessExecutionException e) {
            LOG.error("Error durante la ejecucion del proceso", command, e);
        } catch (IOException e) {
            LOG.error("Error durante la ejecucion del proceso, el proceso no se inicio correctamente", command, e);
        } catch (InterruptedException e) {
            LOG.error("Error durante la ejecucion del proceso, el proceso ha sido interrumpido", command, e);
        }
        return rtrJson;
    }



    private ProcessExecutor processExecutor;

    public LocalProcessesServiceImpl(ProcessExecutor processExecutor) {
        this.processExecutor = processExecutor;
    }
}
