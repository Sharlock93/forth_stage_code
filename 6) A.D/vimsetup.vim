set makeprg=chrome
set wildignore=*.form
nnoremap <A-m> :silent! exe "silent! !chrome localhost/sharo/" . fnameescape( join(split(expand("%:p"), "\\")[2:], "/"))<Enter>


