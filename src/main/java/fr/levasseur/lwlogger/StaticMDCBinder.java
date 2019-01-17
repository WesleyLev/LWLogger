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

    /**
     * Gets singleton.
     *
     * @return the singleton
     */
    public static StaticMDCBinder getSingleton() {
        return SINGLETON;
    }

    /**
     * Gets mdca.
     *
     * @return the mdca
     */
    public MDCAdapter getMDCA() {
        return new NOPMDCAdapter();
    }

    /**
     * Gets mdc adapter class str.
     *
     * @return the mdc adapter class str
     */
    public String getMDCAdapterClassStr() {
        return NOPMDCAdapter.class.getName();
    }

}
