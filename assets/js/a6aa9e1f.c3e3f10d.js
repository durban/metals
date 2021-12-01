"use strict";(self.webpackChunk=self.webpackChunk||[]).push([[3089],{4428:(e,a,t)=>{t.r(a),t.d(a,{default:()=>g});var l=t(7294),r=t(2263),n=t(7635),i=t(3146),s=t(6742),m=t(4973);const o=function(e){var a=e.metadata,t=a.previousPage,r=a.nextPage;return l.createElement("nav",{className:"pagination-nav","aria-label":(0,m.I)({id:"theme.blog.paginator.navAriaLabel",message:"Blog list page navigation",description:"The ARIA label for the blog pagination"})},l.createElement("div",{className:"pagination-nav__item"},t&&l.createElement(s.Z,{className:"pagination-nav__link",to:t},l.createElement("div",{className:"pagination-nav__label"},"\xab"," ",l.createElement(m.Z,{id:"theme.blog.paginator.newerEntries",description:"The label used to navigate to the newer blog posts page (previous page)"},"Newer Entries")))),l.createElement("div",{className:"pagination-nav__item pagination-nav__item--next"},r&&l.createElement(s.Z,{className:"pagination-nav__link",to:r},l.createElement("div",{className:"pagination-nav__label"},l.createElement(m.Z,{id:"theme.blog.paginator.olderEntries",description:"The label used to navigate to the older blog posts page (next page)"},"Older Entries")," ","\xbb"))))};var c=t(5601),d=t(6700);const g=function(e){var a=e.metadata,t=e.items,s=e.sidebar,m=(0,r.default)().siteConfig.title,g=a.blogDescription,p=a.blogTitle,u="/"===a.permalink?m:p;return l.createElement(n.Z,{title:u,description:g,wrapperClassName:d.kM.wrapper.blogPages,pageClassName:d.kM.page.blogListPage,searchMetadatas:{tag:"blog_posts_list"}},l.createElement("div",{className:"container margin-vert--lg"},l.createElement("div",{className:"row"},l.createElement("div",{className:"col col--3"},l.createElement(c.Z,{sidebar:s})),l.createElement("main",{className:"col col--7"},t.map((function(e){var a=e.content;return l.createElement(i.Z,{key:a.metadata.permalink,frontMatter:a.frontMatter,metadata:a.metadata,truncated:a.metadata.truncated},l.createElement(a,null))})),l.createElement(o,{metadata:a})))))}},3146:(e,a,t)=>{t.d(a,{Z:()=>p});var l=t(7294),r=t(6010),n=t(3905),i=t(4973),s=t(6742),m=t(4927),o=t(1217);const c="blogPostTitle_d4p0",d="blogPostDate_iEnO";var g=t(6700);const p=function(e){var a,t,p=(a=(0,g.c2)().selectMessage,function(e){var t=Math.ceil(e);return a(t,(0,i.I)({id:"theme.blog.post.readingTime.plurals",description:'Pluralized label for "{readingTime} min read". Use as much plural forms (separated by "|") as your language support (see https://www.unicode.org/cldr/cldr-aux/charts/34/supplemental/language_plural_rules.html)',message:"One min read|{readingTime} min read"},{readingTime:t}))}),u=e.children,v=e.frontMatter,E=e.metadata,b=e.truncated,h=e.isBlogPostPage,_=void 0!==h&&h,N=E.date,k=E.formattedDate,f=E.permalink,Z=E.tags,w=E.readingTime,T=v.author,L=v.title,M=v.image,I=v.keywords,P=v.author_url||v.authorURL,y=v.author_title||v.authorTitle,x=v.author_image_url||v.authorImageURL;return l.createElement(l.Fragment,null,l.createElement(o.Z,{keywords:I,image:M}),l.createElement("article",{className:_?void 0:"margin-bottom--xl"},(t=_?"h1":"h2",l.createElement("header",null,l.createElement(t,{className:(0,r.Z)("margin-bottom--sm",c)},_?L:l.createElement(s.Z,{to:f},L)),l.createElement("div",{className:"margin-vert--md"},l.createElement("time",{dateTime:N,className:d},k,w&&l.createElement(l.Fragment,null," \xb7 ",p(w)))),l.createElement("div",{className:"avatar margin-vert--md"},x&&l.createElement(s.Z,{className:"avatar__photo-link avatar__photo",href:P},l.createElement("img",{src:x,alt:T})),l.createElement("div",{className:"avatar__intro"},T&&l.createElement(l.Fragment,null,l.createElement("h4",{className:"avatar__name"},l.createElement(s.Z,{href:P},T)),l.createElement("small",{className:"avatar__subtitle"},y)))))),l.createElement("div",{className:"markdown"},l.createElement(n.Zo,{components:m.Z},u)),(Z.length>0||b)&&l.createElement("footer",{className:"row margin-vert--lg"},Z.length>0&&l.createElement("div",{className:"col"},l.createElement("strong",null,l.createElement(i.Z,{id:"theme.tags.tagsListLabel",description:"The label alongside a tag list"},"Tags:")),Z.map((function(e){var a=e.label,t=e.permalink;return l.createElement(s.Z,{key:t,className:"margin-horiz--sm",to:t},a)}))),b&&l.createElement("div",{className:"col text--right"},l.createElement(s.Z,{to:E.permalink,"aria-label":"Read more about "+L},l.createElement("strong",null,l.createElement(i.Z,{id:"theme.blog.post.readMore",description:"The label used in blog post item excerpts to link to full blog posts"},"Read More")))))))}},5601:(e,a,t)=>{t.d(a,{Z:()=>g});var l=t(7294),r=t(6010),n=t(6742);const i="sidebar_q+wC",s="sidebarItemTitle_9G5K",m="sidebarItemList_6T4b",o="sidebarItem_cjdF",c="sidebarItemLink_zyXk",d="sidebarItemLinkActive_wcJs";function g(e){var a=e.sidebar;return 0===a.items.length?null:l.createElement("div",{className:(0,r.Z)(i,"thin-scrollbar")},l.createElement("h3",{className:s},a.title),l.createElement("ul",{className:m},a.items.map((function(e){return l.createElement("li",{key:e.permalink,className:o},l.createElement(n.Z,{isNavLink:!0,to:e.permalink,className:c,activeClassName:d},e.title))}))))}}}]);