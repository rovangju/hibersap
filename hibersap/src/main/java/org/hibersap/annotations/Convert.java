package org.hibersap.annotations;

/*
 * Copyright (C) 2008 akquinet tech@spree GmbH
 * 
 * This file is part of Hibersap.
 *
 * Hibersap is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Hibersap is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Hibersap.  If not, see <http://www.gnu.org/licenses/>.
 */

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import org.hibersap.conversion.Converter;

/**
 * Declare converter that maps SAP data types into Java and vice versa.
 * 
 * @author Carsten Erker
 */
@Retention(RUNTIME)
public @interface Convert {
	Class<? extends Converter> converter();
}
