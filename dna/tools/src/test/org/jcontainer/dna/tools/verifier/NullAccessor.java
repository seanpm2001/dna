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

/**
 *
 * @author Peter Donald
 * @version $Revision: 1.4 $ $Date: 2004-04-18 14:44:19 $
 */
class NullAccessor
    implements MetaClassAccessor
{
    public ClassDescriptor getClassDescriptor( final String classname,
                                               final ClassLoader classLoader,
                                               final MetaClassAccessor accessor )
        throws MetaClassException
    {
        return null;
    }
}
