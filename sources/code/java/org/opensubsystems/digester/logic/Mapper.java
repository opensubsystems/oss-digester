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

package org.opensubsystems.digester.logic;

import org.opensubsystems.digester.data.Record;
import org.opensubsystems.digester.data.DigesterContext;
import org.opensubsystems.core.error.OSSException;

/**
 * Mapper responsible for transforming content supplied by reader to data objects.  
 * 
 * @author bastafidli
 */
public interface Mapper<C extends DigesterContext, R extends Record> 
{
  /**
   * Create the data object from the record read by the reader.
   * 
   * @param context - context within which the record is processed
   * @param record - record for which the data object is being created
   * @return Object - data object created from the supplied record or null if no 
   *                  data object should be created for this record and this record 
   *                  should be skipped. Record can be skipped for example when 
   *                  it is cached in the context and will be processed later.
   * @throws OSSException - an error has occurred
   */
  Object createObject(
    C context,
    R record
  ) throws OSSException;

//  /**
//   * Process record, which was requested to be skipped. Since the skipped record
//   * doesn't have to follow the format for the file (e.g. it was requested to be 
//   * skipped because of incorrect format), the whole record is passed as 
//   * parameter without being parsed.
//   * 
//   * @param record - record which was skipped
//   */
//  void processSkippedRecord(
//    R record
//  );
//
//  /**
//   * Set element (one part of the record) mapped to attribute (or set of 
//   * attributes) of the data object to specified value. 
//   * 
//   * @param data - data object previously created by createObject method to set
//   * @param iElementOrder - 1 based order number of the element in record to set
//   * @param value - value of the element to set the attribute to
//   */
//  void mapElement(
//    Object data, 
//    int    iElementOrder, 
//    String value
//  );
//
//  /**
//   * Test if two records are related and therefore they needs to be processed
//   * together (e.g. the data object is split into multiple records)
//   * 
//   * @param previousRecord - previous record
//   * @param currentRecord - current record which should be tested
//   * @return boolean - true if current record is related to the previous record
//   *                   false otherwise
//   */
//  boolean isRelatedRecord(
//    R previousRecord, 
//    R currentRecord
//  );
}
