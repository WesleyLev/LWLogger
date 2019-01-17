package fr.levasseur.lwlogger;

import org.slf4j.helpers.NOPMDCAdapter;
import org.slf4j.spi.MDCAdapter;

/**
 * Class © LEVASSEUR
 *
 * @author Wesley LEVASSEUR
 * @version 1.0.0
 */
public class StaticMDCBinder {

    private static final StaticMDCBinder SINGLETON = new StaticMDCBinder();

    private StaticMDCBinder() {
    }

    public static StaticMDCBinder getSingleton() {
        return SINGLETON;
    }

    public MDCAdapter getMDCA() {
        return new NOPMDCAdapter();
    }

    public String getMDCAdapterClassStr() {
        return NOPMDCAdapter.class.getName();
    }

}
