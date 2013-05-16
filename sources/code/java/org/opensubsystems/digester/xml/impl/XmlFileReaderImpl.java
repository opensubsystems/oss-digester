/*
 * Copyright (C) 2003 - 2013 OpenSubsystems.com/net/org and its owners. All rights reserved.
 * 
 * This file is part of OpenSubsystems.
 *
 * OpenSubsystems is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>. 
 */

package org.opensubsystems.digester.xml.impl;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.apache.commons.digester3.binder.RulesModule;
import org.opensubsystems.digester.*;
import org.opensubsystems.core.error.OSSException;
import org.opensubsystems.core.error.OSSMultiException;
import org.opensubsystems.core.logic.impl.StatelessControllerImpl;
import org.opensubsystems.core.util.GlobalConstants;
import org.opensubsystems.core.util.Log;
import org.xml.sax.SAXException;

/**
 * Implementation of the reader interface using the commons digester package.
 *
 * @author bastafidli
 */
public class XmlFileReaderImpl<C extends DigesterContext, R> extends StatelessControllerImpl
                                                             implements Reader<File, C, R>
{
   // Attributes ///////////////////////////////////////////////////////////////
   
   /**
    * Rules describing how to digest the XML input into Java objects.
    */
   RulesModule m_rules;
   
   // Cached values ////////////////////////////////////////////////////////////

   /**
    * Logger for this class
    */
   private static Logger s_logger = Log.getInstance(XmlFileReaderImpl.class);

   // Constructors /////////////////////////////////////////////////////////////
   
   /**
    * Default constructor. The rules modules has to be initialized using context.
    */
   public XmlFileReaderImpl()
   {
      // Do nothing
      // TODO: The rules module has to be supplied via the context
   }
   
   /**
    * Constructor explicitly specifying rule module to use.
    * 
    * @param rules - Rules describing how to digest the XML input into Java 
    *                objects.
    */
   public XmlFileReaderImpl(
      RulesModule rules
   )
   {
      m_rules = rules;
   }
   
   // Logic ////////////////////////////////////////////////////////////////////
   
   /**
    * {@inheritDoc}
    */
   @Override
   public void open(
      C    context,
      File source
   ) throws OSSException
   {
      if (GlobalConstants.ERROR_CHECKING)
      {
         assert m_rules != null : 
                "No rules modules configured to digest file " 
                + source.getAbsolutePath(); 
      }
      context.setOpenedSource(source);
      s_logger.log(Level.FINE, "Opened file {0}", source.getAbsolutePath());
   }
   
   /**
    * {@inheritDoc}
    */
   @Override
   public R read(
      C context
   ) throws OSSException
   {
      R        record = null;
      Digester digester;
      Object   source;

      source = context.getOpenedSource();
      if (GlobalConstants.ERROR_CHECKING)
      {
         assert source != null : 
                "No opened source is available to read from."; 
         assert source instanceof File : 
                "The opened source is not a " + File.class 
                + ", it is instead a " + source.getClass(); 
      }

      File file = (File)source;
         
      try
      {
         digester = DigesterLoader.newLoader(m_rules).newDigester();
         s_logger.log(Level.FINE, "Parsing file {0}", file.getCanonicalPath());
         record = digester.parse(file);
         s_logger.log(Level.FINE, "Finished parsing file {0}", file.getCanonicalPath());
      }
      catch (SAXException | IOException exc)
      {
         throw new OSSMultiException("Error parsing input file " 
                                     + file.getAbsolutePath(), exc);
      }
      
      return record;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public void close(
      C context
   ) throws OSSException
   {
      context.setOpenedSource(null);
   }
}
