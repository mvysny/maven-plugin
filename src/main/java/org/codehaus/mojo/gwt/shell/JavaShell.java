package org.codehaus.mojo.gwt.shell;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


import java.util.ArrayList;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;
import org.codehaus.plexus.util.cli.shell.Shell;

/**
 * plexus-util hack to run a command WITHOUT a shell PLXUTILS-107
 */
public class JavaShell
    extends Shell
{
    protected List<String> getRawCommandLine( String executable, String[] arguments )
    {
        List<String> commandLine = new ArrayList<String>();
        if ( executable != null )
        {
            commandLine.add( executable );
        }
        for ( String arg : arguments )
        {
            if ( isQuotedArgumentsEnabled() )
            {
                char[] escapeChars = getEscapeChars( isSingleQuotedExecutableEscaped(),
                                                     isDoubleQuotedExecutableEscaped() );

                commandLine.add( StringUtils.quoteAndEscape( arg, getArgumentQuoteDelimiter(), escapeChars,
                                                             getQuotingTriggerChars(), '\\', false ) );
            }
            else
            {
                commandLine.add( arg );
            }
        }
        return commandLine;
    }
}