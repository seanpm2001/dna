/*
 * Copyright (C) The Spice Group. All rights reserved.
 *
 * This software is published under the terms of the Spice
 * Software License version 1.1, a copy of which has been included
 * with this distribution in the LICENSE.txt file.
 */
package org.jcontainer.dna.tools.verifier;

import org.codehaus.metaclass.introspector.MetaClassAccessor;
import org.codehaus.metaclass.introspector.MetaClassException;
import org.codehaus.metaclass.model.ClassDescriptor;
import org.codehaus.metaclass.model.Attribute;
import org.codehaus.metaclass.model.FieldDescriptor;
import org.codehaus.metaclass.model.MethodDescriptor;
import java.util.Properties;
import java.awt.event.ActionListener;

/**
 *
 * @author Peter Donald
 * @version $Revision: 1.4 $ $Date: 2004-04-18 14:44:19 $
 */
class BadServiceAccessor
    implements MetaClassAccessor
{
    static final String BAD_SERVICE = "I-No-Exist!";

    public ClassDescriptor getClassDescriptor( final String classname,
                                               final ClassLoader classLoader,
                                               final MetaClassAccessor accessor )
        throws MetaClassException
    {
        final Properties bad = new Properties();
        bad.setProperty( "type", ActionListener.class.getName() );

        final Properties good = new Properties();
        good.setProperty( "type", BAD_SERVICE );
        final Attribute[] attributes = new Attribute[]
        {
            new Attribute( "dna.component" ),
            new Attribute( "dna.service", bad ),
            new Attribute( "dna.service", good )
        };
        return new ClassDescriptor( classname,
                                    attributes,
                                    attributes,
                                    FieldDescriptor.EMPTY_SET,
                                    MethodDescriptor.EMPTY_SET );
    }
}
