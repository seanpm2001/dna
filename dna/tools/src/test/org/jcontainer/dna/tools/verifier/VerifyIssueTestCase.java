/*
 * Copyright (C) The Spice Group. All rights reserved.
 *
 * This software is published under the terms of the Spice
 * Software License version 1.1, a copy of which has been included
 * with this distribution in the LICENSE.txt file.
 */
package org.jcontainer.dna.tools.verifier;

import junit.framework.TestCase;

/**
 *
 * @author <a href="mailto:peter at realityforge.org">Peter Donald</a>
 * @version $Revision: 1.1 $ $Date: 2003-10-25 10:32:42 $
 */
public class VerifyIssueTestCase
    extends TestCase
{
    public void testVerifyIssueAsError()
        throws Exception
    {
        final String description = "Here I stand.";
        final int level = VerifyIssue.ERROR;
        final VerifyIssue issue = new VerifyIssue( level, description );
        assertEquals( "description", description, issue.getDescription() );
        assertEquals( "isError", true, issue.isError() );
        assertEquals( "isWarning", false, issue.isWarning() );
    }

    public void testVerifyIssueAsWarning()
        throws Exception
    {
        final String description = "Here I stand.";
        final int level = VerifyIssue.WARNING;
        final VerifyIssue issue = new VerifyIssue( level, description );
        assertEquals( "description", description, issue.getDescription() );
        assertEquals( "isError", false, issue.isError() );
        assertEquals( "isWarning", true, issue.isWarning() );
    }

    public void testVerifyIssueAsNotice()
        throws Exception
    {
        final String description = "Here I stand.";
        final int level = VerifyIssue.NOTICE;
        final VerifyIssue issue = new VerifyIssue( level, description );
        assertEquals( "description", description, issue.getDescription() );
        assertEquals( "isError", false, issue.isError() );
        assertEquals( "isWarning", false, issue.isWarning() );
    }

    public void testNulldescriptionPassedToCtor()
        throws Exception
    {
        try
        {
            new VerifyIssue( VerifyIssue.ERROR, null );
        }
        catch( final NullPointerException npe )
        {
            assertEquals( "npe.getMessage()", "description", npe.getMessage() );
            return;
        }
        fail( "Expected to fail due to null description passed into Ctor" );
    }
}
