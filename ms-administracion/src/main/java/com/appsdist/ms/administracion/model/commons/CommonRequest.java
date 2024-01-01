package com.appsdist.ms.administracion.model.commons;

import lombok.Data;

import java.io.Serializable;

/**
 * The type Common request.
 */
@Data
public class CommonRequest implements Serializable {
    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 7526471155622776147L;
    /**
     * The Transaction id.
     */
    private String transactionId;
    /**
     * The System id.
     */
    private String systemId;
}
