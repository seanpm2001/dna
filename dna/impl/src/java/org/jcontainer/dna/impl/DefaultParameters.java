/*
 * Copyright (C) The Spice Group. All rights reserved.
 *
 * This software is published under the terms of the Spice
 * Software License version 1.1, a copy of which has been included
 * with this distribution in the LICENSE.txt file.
 */
package org.jcontainer.dna.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.jcontainer.dna.ParameterException;
import org.jcontainer.dna.Parameters;

/**
 * Parameters implementation backed by a Properties object.
 * The developer should create the DefaultParameters,
 * associate resources with locator and then invoke
 * {@link #makeReadOnly()} before passing the Locator to
 * the client component.
 *
 * @author <a href="mailto:peter at realityforge.org">Peter Donald</a>
 * @version $Revision: 1.7 $ $Date: 2003-09-08 00:04:09 $
 */
public class DefaultParameters
   extends AbstractFreezable
   implements Parameters
{
   /**
    * Constant for separator between parameters
    * and child parameters.
    */
   private static final String SEPARATOR = ".";

   /**
    * Constant for empty prefix.
    */
   private static final String EMPTY_PREFIX = "";

   /**
    * The key-value pairs contained by parameters object.
    */
   private final Properties m_parameters = new Properties();

   /**
    * The child parameters objects created from with
    * parameters object.
    */
   private final Set m_children = new HashSet();

   /**
    * The prefix associated with parameters object.
    */
   private final String m_prefix;

   /**
    * Create a parameters object with empty prefix.
    */
   public DefaultParameters()
   {
      this( EMPTY_PREFIX );
   }

   /**
    * Create a parameters object with specified prefix.
    *
    * @param prefix the prefix
    */
   public DefaultParameters( final String prefix )
   {
      if ( null == prefix )
      {
         throw new NullPointerException( "prefix" );
      }
      m_prefix = prefix;
   }

   /**
    * Return the names of all the parameters.
    *
    * @return the names of all the parameters.
    */
   public String[] getParameterNames()
   {
      final Set set = getParameters().keySet();
      return (String[]) set.toArray( new String[ set.size() ] );
   }

   /**
    * Return true of parameter with specified name exists.
    *
    * @param name the name
    * @return true of parameter with specified name exists.
    */
   public boolean isParameter( final String name )
   {
      if ( null == name )
      {
         throw new NullPointerException( "name" );
      }
      return getParameters().containsKey( name );
   }

   /**
    * Return value of parameter with specified name.
    *
    * @param name the name
    * @return the value
    * @throws ParameterException if unable to locate parameter
    */
   public String getParameter( final String name )
      throws ParameterException
   {
      if ( null == name )
      {
         throw new NullPointerException( "name" );
      }
      final String property = getParameters().getProperty( name );
      if ( null == property )
      {
         final String message =
            "Unable to locate parameter named " + name;
         throw new ParameterException( message, name );
      }
      return property;
   }

   /**
    * Return value of parameter with specified name.
    *
    * @param name the name
    * @param defaultValue the defaultValue if specified parameter
    *        does not exist
    * @return the value
    */
   public String getParameter( final String name,
                               final String defaultValue )
   {
      if ( null == name )
      {
         throw new NullPointerException( "name" );
      }
      final String fullname = getPrefix() + name;
      return getParameters().getProperty( fullname, defaultValue );
   }

   /**
    * Return value of parameter with specified name as a boolean.
    *
    * @param name the name
    * @return the value
    * @throws ParameterException if unable to locate parameter
    *         or parameter can not be converted to correct type
    */
   public boolean getParameterAsBoolean( final String name )
      throws ParameterException
   {
      return getParameter( name ).equals( "true" );
   }

   /**
    * Return value of parameter with specified name as a boolean.
    *
    * @param name the name
    * @param defaultValue the defaultValue if specified parameter
    *        does not exist or parameter can not be converted to
    *        the correct type
    * @return the value
    */
   public boolean getParameterAsBoolean( final String name,
                                         final boolean defaultValue )
   {
      final String value = getParameter( name, null );
      if ( null == value )
      {
         return defaultValue;
      }
      else
      {
         return value.equals( "true" );
      }
   }

   /**
    * Return value of parameter with specified name as an integer.
    *
    * @param name the name
    * @return the value
    * @throws ParameterException if unable to locate parameter
    *         or parameter can not be converted to correct type
    */
   public int getParameterAsInteger( final String name )
      throws ParameterException
   {
      final String value = getParameter( name );
      try
      {
         return Integer.parseInt( value );
      }
      catch ( final NumberFormatException nfe )
      {
         final String message =
            "Unable to parse parameter named " + name +
            " with value '" + value + "'";
         throw new ParameterException( message, name, nfe );
      }
   }


   /**
    * Return value of parameter with specified name as an integer.
    *
    * @param name the name
    * @param defaultValue the defaultValue if specified parameter
    *        does not exist or parameter can not be converted to
    *        the correct type
    * @return the value
    */
   public int getParameterAsInteger( final String name,
                                     final int defaultValue )
   {
      final String value = getParameter( name, null );
      if ( null == value )
      {
         return defaultValue;
      }
      else
      {
         try
         {
            return Integer.parseInt( value );
         }
         catch ( final NumberFormatException nfe )
         {
            return defaultValue;
         }
      }
   }

   /**
    * Return value of parameter with specified name as a long.
    *
    * @param name the name
    * @return the value
    * @throws ParameterException if unable to locate parameter
    *         or parameter can not be converted to correct type
    */
   public long getParameterAsLong( final String name )
      throws ParameterException
   {
      final String value = getParameter( name );
      try
      {
         return Long.parseLong( value );
      }
      catch ( final NumberFormatException nfe )
      {
         final String message =
            "Unable to parse parameter named " + name +
            " with value '" + value + "'";
         throw new ParameterException( message, name, nfe );
      }
   }

   /**
    * Return value of parameter with specified name as a long.
    *
    * @param name the name
    * @param defaultValue the defaultValue if specified parameter
    *        does not exist or parameter can not be converted to
    *        the correct type
    * @return the value
    */
   public long getParameterAsLong( final String name,
                                   final long defaultValue )
   {
      final String value = getParameter( name, null );
      if ( null == value )
      {
         return defaultValue;
      }
      else
      {
         try
         {
            return Long.parseLong( value );
         }
         catch ( final NumberFormatException nfe )
         {
            return defaultValue;
         }
      }
   }

   /**
    * Return value of parameter with specified name as a float.
    *
    * @param name the name
    * @return the value
    * @throws ParameterException if unable to locate parameter
    *         or parameter can not be converted to correct type
    */
   public float getParameterAsFloat( final String name )
      throws ParameterException
   {
      final String value = getParameter( name );
      try
      {
         return Float.parseFloat( value );
      }
      catch ( final NumberFormatException nfe )
      {
         final String message =
            "Unable to parse parameter named " + name +
            " with value '" + value + "'";
         throw new ParameterException( message, name, nfe );
      }
   }

   /**
    * Return value of parameter with specified name as a float.
    *
    * @param name the name
    * @param defaultValue the defaultValue if specified parameter
    *        does not exist or parameter can not be converted to
    *        the correct type
    * @return the value
    */
   public float getParameterAsFloat( final String name,
                                     final float defaultValue )
   {
      final String value = getParameter( name, null );
      if ( null == value )
      {
         return defaultValue;
      }
      else
      {
         try
         {
            return Float.parseFloat( value );
         }
         catch ( final NumberFormatException nfe )
         {
            return defaultValue;
         }
      }
   }

   /**
    * Return a Parameters object that represents a
    * subset of parameters with specified prefix + the '.'
    * character. if the prefix was "foo" then the parameter
    * "foo.baz" would be included in child Parameters object
    * using the key "baz".
    *
    * @param prefix the prefix
    * @return the parameters object
    */
   public Parameters getChildParameters( final String prefix )
   {
      if ( null == prefix )
      {
         throw new NullPointerException( "prefix" );
      }
      final String prefixAndSeparator = prefix + SEPARATOR;
      final int length = prefix.length() + 1;
      final DefaultParameters parameters = new DefaultParameters( getPrefix() + SEPARATOR + prefix );
      final Iterator iterator = getParameters().keySet().iterator();
      while ( iterator.hasNext() )
      {
         final String key = (String) iterator.next();
         if ( key.startsWith( prefixAndSeparator ) )
         {
            final String value = getParameter( key, null );
            final String newKey = key.substring( length );
            parameters.setParameter( newKey, value );
         }
      }

      parameters.makeReadOnly();
      m_children.add( parameters );
      return parameters;
   }

   /**
    * Mark the resource and all child parameter
    * objects as read only.
    */
   public void makeReadOnly()
   {
      super.makeReadOnly();
      final Iterator iterator = m_children.iterator();
      while ( iterator.hasNext() )
      {
         final Object child = iterator.next();
         if ( child instanceof Freezable )
         {
            ( (Freezable) child ).makeReadOnly();
         }
      }
   }

   /**
    * Set parameter with specified name to specified value.
    *
    * @param name the parameter name
    * @param value the parameter value
    */
   public void setParameter( final String name,
                             final String value )
   {
      if ( null == name )
      {
         throw new NullPointerException( "name" );
      }
      if ( null == value )
      {
         throw new NullPointerException( "value" );
      }
      checkWriteable();
      getParameters().setProperty( name, value );
   }

   /**
    * Return the backing properties object associated with parameters.
    *
    * @return the backing properties object associated with parameters.
    */
   protected final Properties getParameters()
   {
      return m_parameters;
   }

   /**
    * Return the prefix associated with Parameters object.
    *
    * @return the prefix associated with Parameters object.
    */
   protected final String getPrefix()
   {
      return m_prefix;
   }
}
