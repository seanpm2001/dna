/*
 * Copyright (C) The Spice Group. All rights reserved.
 *
 * This software is published under the terms of the Spice
 * Software License version 1.1, a copy of which has been included
 * with this distribution in the LICENSE.txt file.
 */
package org.jcontainer.dna;

/**
 * Utility class to signal to the container that
 * a resource is no longer going to be used by
 * the component.
 *
 * @version $Revision: 1.2 $ $Date: 2003-09-05 06:01:51 $
 */
public class ReleaseUtil
{
   /**
    * Utility interface used to mark resources that
    * can be released. Developers should never directly
    * reference this class and never make a component
    * implement this interface. Instead the container
    * will choose to have the proxys of component implement
    * the interface.
    */
   public interface Releaseable
   {
      /**
       * Indicate to the container that component no
       * longer needs resources.
       */
      void release();
   }

   /**
    * The component invokes this method to indicate to
    * the container that it no longer needs specified
    * resource.
    *
    * @param object the resource
    */
   public static void release( final Object object )
   {
      if ( object instanceof Releaseable )
      {
         ( (Releaseable) object ).release();
      }
   }
}
