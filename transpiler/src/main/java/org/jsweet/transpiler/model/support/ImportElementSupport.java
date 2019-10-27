/* 
 * JSweet transpiler - http://www.jsweet.org
 * Copyright (C) 2015 CINCHEO SAS <renaud.pawlak@cincheo.fr>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *  
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.jsweet.transpiler.model.support;

import javax.lang.model.element.TypeElement;

import org.jsweet.transpiler.JSweetContext;
import org.jsweet.transpiler.model.ImportElement;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.ImportTree;
import com.sun.source.tree.Tree;

/**
 * See {@link ImportElement}.
 * 
 * @author Renaud Pawlak
 * @author Louis Grignon
 */
public class ImportElementSupport extends ExtendedElementSupport<ImportTree> implements ImportElement {

	public ImportElementSupport(CompilationUnitTree compilationUnit, ImportTree tree, JSweetContext context) {
		super(compilationUnit, tree, context);
	}

	@Override
	public boolean isStatic() {
		return getTree().isStatic();
	}

	@Override
	public TypeElement getImportedType() {
		Tree importedIdentifier = getTree().getQualifiedIdentifier();
		if (getTree().isStatic() || importedIdentifier.type == null) {
			return null;
		} else {
			return (TypeElement) importedIdentifier.type.tsym;
		}
	}

	@Override
	public boolean isWildcard() {
		return getTree().toString().endsWith("*");
	}

}
