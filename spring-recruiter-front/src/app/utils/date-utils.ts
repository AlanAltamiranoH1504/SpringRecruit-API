import {formatDistanceToNow} from 'date-fns';
import {es} from 'date-fns/locale';

export const getTimeAgo = (date: string) => {
  return formatDistanceToNow(new Date(date), {
    addSuffix: true,
    locale: es
  });
}
