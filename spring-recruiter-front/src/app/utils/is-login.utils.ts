export const isLogin = (): boolean => {
  const tokenLocalStorage = localStorage.getItem("tokenSpringRecruiter");
  if (tokenLocalStorage && localStorage !== null && localStorage !== undefined) {
    return true;
  }
  return false;
}
