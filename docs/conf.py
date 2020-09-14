# -*- coding: utf-8 -*-
#
# Buggy documentation build configuration file

import sys
import os

needs_sphinx = '1.7'

sys.path.append(os.path.abspath('extensions'))
extensions = ['sphinx.ext.imgmath']
templates_path = ['_templates']

source_suffix = '.rst'
source_encoding = 'utf-8-sig'

# The master toctree document
master_doc = 'index'

# General information about the project
project = 'TestRail4J'
copyright = '2019 Oleg Shaburov. Maintained by the TouchBIT Team'
author = 'Oleg Shaburov'

version = 'master'
release = 'latest'

language = 'en'

exclude_patterns = ['_build']

from sphinx.highlighting import lexers

pygments_style = 'monokai'
# highlight_language = 'java'

on_rtd = os.environ.get('READTHEDOCS', None) == 'True'

import sphinx_rtd_theme

html_theme = 'sphinx_rtd_theme'
html_theme_path = [sphinx_rtd_theme.get_html_theme_path()]
if on_rtd:
    using_rtd_theme = True

# Theme options
html_theme_options = {
    # 'typekit_id': 'hiw1hhg',
    # 'analytics_id': '',
    # 'sticky_navigation': True  # Set to False to disable the sticky nav while scrolling.
    'logo_only': True,  # if we have a html_logo below, this shows /only/ the logo with no title text
    'collapse_navigation': False,  # Collapse navigation (False makes it tree-like)
    'display_version': True,  # Display the docs version
    # 'navigation_depth': 4,  # Depth of the headers shown in the navigation bar
}

html_extra_path = ['robots.txt']

html_logo = 'img/testrail4j_logo.png'

# Output file base name for HTML help builder
htmlhelp_basename = 'TestRail4J'

# -- Options for reStructuredText parser ----------------------------------

# Enable directives that insert the contents of external files
file_insertion_enabled = False

# disable checking urls with about.html#this_part_of_page anchors
linkcheck_anchors = False
linkcheck_timeout = 10
